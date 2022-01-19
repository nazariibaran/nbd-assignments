#!/usr/bin/env python.

import riak

def addDocument(bucket, key, data):
    newDocument = bucket.new(key, data)
    newDocument.store()
    if bucket.get(key).exists:
        print("Document added : " + key)
    else:
        print("Failed to add doc '%s' to database" % key)
        assert bucket.get(key).exists == True

def fetchDocument(bucket, key):
    return bucket.get(key)

def fetchAndPrintDocument(bucket, key):
    document = fetchDocument(bucket, key)
    if document.exists:
        print("Document: %s - %s" % (key, document.encoded_data))
    else:
        print("Unable to find doc: %s (Document: %s)" % (key, document.encoded_data))
        assert document.exists == True

def updateDocument(bucket, key, item, newValue):
    document = fetchDocument(bucket, key)
    document.data[item] = newValue
    document.store()
    if document.data[item] == newValue:
        print("Document: %s - has been updated (changed field '%s' to %s)" % (key, item, str(newValue)))
    else:
        print("Document: %s - Hasn't been updated (field '%s' = %s)" % (key, item, document.data[item]))
        assert document.data[item] == newValue

def deleteDocument(bucket, key):
    document = fetchDocument(bucket, key)
    document.delete()
    if document.data == None:
        print("Document: %s - has been deleted" % key)
    else:
        print("Document: %s - hasn't been deleted" % key)
        assert document.data == None

if __name__ == "__main__":
    myClient = riak.RiakClient(port = 8098)
    myBucket = myClient.bucket('s24068')

    car = {
        'manufacturer': "Tesla",
        'model': "Model 3",
        'year': 2020,
        'price': 50000
    }

    addDocument(myBucket, str(car['manufacturer']), car)

    fetchAndPrintDocument(myBucket, str(car['manufacturer']))

    updateDocument(myBucket, str(car['manufacturer']), "price", 75000)

    fetchAndPrintDocument(myBucket, str(car['manufacturer']))

    deleteDocument(myBucket, str(car['manufacturer']))

    fetchAndPrintDocument(myBucket, str(car['manufacturer']))
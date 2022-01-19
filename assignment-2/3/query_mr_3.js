const map = function () {
  emit(this.job, 1);
};

const result = {
  out: "jobList"
};

db.assignment2.mapReduce(map, null, result)

printjson(db.jobList.find({}, { id: 1 }).toArray())

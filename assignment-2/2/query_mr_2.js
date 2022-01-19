const map = function () {
  for (let i = 0; i < this.credit.length; i++)
    emit(this.credit[i].currency, { balance: this.credit[i].balance });
};

const reduce = function (key, value) {
  total = { balance: 0 }

  for (let i = 0; i < value.length; i++) {
    total.balance += parseFloat(value[i].balance)
  }

  return total;
};

function getTotalBalance(key, value) {
  return {
    total: value.balance
  };
}

var result = { out: "totalBalance", finalize: getTotalBalance };

db.assignment2.mapReduce(map, reduce, result)

printjson(db.totalBalance.find({}).toArray())

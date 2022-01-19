const map = function () {
  if (this.sex == "Female" && this.nationality == "Poland") {
    for (let i = 0; i < this.credit.length; i++)
      emit(this.credit[i].currency, { balance: this.credit[i].balance, count: 1 });
  }
};

const reduce = function (key, value) {
  const reducedVal = { balance: 0, count: 0 }

  value.forEach(account => {
    reducedVal.count += account.count;
    reducedVal.balance += parseFloat(account.balance);
  })

  return reducedVal;
};

function getResult(key, value) {
  return { avgBalance: value.balance / value.count, totalBalance: value.balance };
}

const result = { out: "avgAndTotalBalance", finalize: getResult };

db.assignment2.mapReduce(map, reduce, result)

printjson(db.avgAndTotalBalance.find({}).toArray())

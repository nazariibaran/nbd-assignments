const map = function () {
  emit(this.sex, { sumH: this.height, sumW: this.weight, count: 1 });
};

const reduce = function (key, value) {
  total = { sumH: 0, sumW: 0, count: 0 };

  for (let i = 0; i < value.length; i++) {
    total.sumH += parseFloat(value[i].sumH);
    total.sumW += parseFloat(value[i].sumW);
    total.count += value[i].count;
  }

  return total;
};

function getAverage(key, value) {
  return {
    avgHeight: value.sumH / value.count,
    avgWeight: value.sumW / value.count
  };
}

const result = { out: "avgHeightWeight", finalize: getAverage };

db.assignment2.mapReduce(map, reduce, result)

printjson(db.avgHeightWeight.find({}).toArray())

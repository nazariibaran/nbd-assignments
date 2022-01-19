const map = function () {
  emit(this.nationality, { H: [this.height], W: [this.weight], count: 1 });
};

const reduce = function (k, value) {
  crlist = { H: [], W: [], count: 0 };

  for (let i = 0; i < value.length; i++) {
    crlist.H = value[i].H.concat(crlist.H);
    crlist.W = value[i].W.concat(crlist.W);
    crlist.count += value[i].count;
  }

  return crlist;
};

function getBMI(key, value) {
  let BMI = 0, minBMI = value.W[0] / value.H[0] / value.H[0] * 10000, maxBMI = minBMI;

  for (let i = 0; i < value.W.length; i++) {
    BMI = value.W[i] / value.H[i] / value.H[i] * 10000;
    minBMI = BMI < minBMI ? BMI : minBMI
    maxBMI = BMI > maxBMI ? BMI : maxBMI
    BMI += BMI
  }
  return {
    avgBMI: BMI / value.count, minBMI: minBMI, maxBMI: maxBMI
  };
};

db.assignment2.mapReduce(map, reduce, { out: "BMI", finalize: getBMI })

printjson(db.BMI.find().toArray())


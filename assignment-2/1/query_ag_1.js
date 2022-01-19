use('PersonDB');

db.qwerty.aggregate([
  {
    $group: { _id: "$sex", avgWeight: { $avg: "$weight" }, avgHeight: { $avg: "$height" } }
  }
])

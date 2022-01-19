use('PersonDB');

db.people.aggregate([
  {
    $match: {
      sex: "Female",
      nationality: "Poland"
    }
  },
  {
    $unwind: {
      path: "$credit",
      preserveNullAndEmptyArrays: true
    }
  },
  {
    $group:
    {
      _id: "$credit.currency",
      avgBalance: {
        $avg: "$credit.balance"
      },
      totalBalance: {
        $sum: "$credit.balance"
      }
    }
  },
  {
    $sort: { "_id": 1 }
  }
])
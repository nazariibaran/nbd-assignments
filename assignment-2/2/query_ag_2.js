use('PersonDB');

db.people.aggregate([
  { $unwind: { path: "$credit", preserveNullAndEmptyArrays: true } },
  { $group: { _id: "$credit.currency", totalBalance: { $sum: "$credit.balance" } } },
  { $sort: { "_id": 1 } }
])
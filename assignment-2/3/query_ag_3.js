use('PersonDB');

db.people.aggregate([
  { $group: { _id: "$job" } }
])

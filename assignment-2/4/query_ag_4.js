use('PersonDB');

db.people.aggregate([
  {
    $addFields: {
      BMI: {
        $divide:
          [{
            $toDouble: "$weight"
          },
          {
            $pow:
              [{
                $divide:
                  [{ $toDouble: "$height" }, 100]
              }, 2]
          }]
      }
    }
  },
  {
    $group:
    {
      _id: "$nationality",
      avgBMI: { $avg: "$BMI" },
      minBMI: { $min: "$BMI" },
      maxBMI: { $max: "$BMI" }
    }
  }])
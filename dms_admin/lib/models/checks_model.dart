class CheckIn {
  final String? username;
  final DateTime? date;
  final DateTime? time;
  final bool? isLate;
  final bool? userCheckedIn;

  CheckIn({
    required this.username,
    required this.date,
    required this.time,
    required this.isLate,
    required this.userCheckedIn
  });

  factory CheckIn.fromJson(Map<String, dynamic> json) {
    return CheckIn(
      username: json['username'],
      date: json['date'],
      time: json['time'],
      isLate: json['userLate'],
      userCheckedIn: json['userCheckedIn']
    );
  }

  Map<String, dynamic> toJson() => {
    "username": username,
    "date": date,
    "time": time,
    "isLate": isLate,
    "userCheckedIn": userCheckedIn
  };
}

class CheckOut {
  final String? username;
  final DateTime? date;
  final DateTime? time;
  final bool? userCheckedOutLate;
  final bool? userCheckedOut;

  CheckOut({
    required this.username,
    required this.date,
    required this.time,
    required this.userCheckedOut,
    required this.userCheckedOutLate
  });

  factory CheckOut.fromJson(Map<String, dynamic> json) {
    return CheckOut(
        username: json['username'],
        date: json['date'],
        time: json['time'],
        userCheckedOut: json['userCheckedOut'],
        userCheckedOutLate: json['didUserCheckOutLate']
    );
  }

  Map<String, dynamic> toJson() => {
    "username": username,
    "date": date,
    "time": time,
    "userCheckedOut": userCheckedOut,
    "userCheckedOutLate": userCheckedOutLate
  };
}
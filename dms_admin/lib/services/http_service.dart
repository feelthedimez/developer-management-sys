import 'package:dms_admin/config/config_data.dart';
import 'package:dms_admin/models/checks_model.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';


class HttpService {
  Future<CheckIn> getCheckInByUserNameAndDate(String username, String date) async {
    var response = await http.get(Uri.parse(url + '$username/$date'));
    if (response.statusCode == 200) {
      return CheckIn.fromJson(jsonDecode(response.body));
    }
    return jsonDecode(response.body);
  }
}
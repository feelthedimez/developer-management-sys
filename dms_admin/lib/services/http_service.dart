import 'package:dms_admin/config/config_data.dart';
import 'package:dms_admin/models/checks_model.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class HttpService {
  static Future<CheckIn> getAllCheckIn() async {
    var response = await http.get(Uri.parse(url + '/avail/checkin/tetema/2022-03-26'));
    if (response.statusCode == 200) {
      return CheckIn.fromJson(jsonDecode(response.body));
    }
    return jsonDecode(response.body);
  }


}
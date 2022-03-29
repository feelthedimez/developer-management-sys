import 'package:dms_admin/config/config_data.dart';
import 'package:dms_admin/models/checks_model.dart';
import 'package:dms_admin/services/http_service.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';



class CheckInController extends GetxController {

  var checkIns = <CheckIn>[].obs;
  var isLoading = true.obs;

  @override
  void onInit() {
    super.onInit();
    getCheckInData();
  }

  void getCheckInData() async {
    try {
      isLoading(true);
      var allCheckIns = await HttpService.getAllCheckIn();
      if (allCheckIns != null) {
        checkIns.value = allCheckIns as List<CheckIn>;
      }
    } finally {
      isLoading(false);
    }
  }
}
import 'package:dms_admin/controllers/check_in_controller.dart';
import 'package:get/get.dart';

class ControllerBinding extends Bindings {
  @override
  void dependencies() {
    Get.put<CheckInController>(CheckInController());
  }

}
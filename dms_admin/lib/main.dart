import 'package:dms_admin/binders.dart';
import 'package:dms_admin/controllers/check_in_controller.dart';
import 'package:dms_admin/view/home_view.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(const DMSApplication());
}

class DMSApplication extends StatelessWidget {
  const DMSApplication({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: "DMS Admin Application",
      initialBinding: ControllerBinding(),
      debugShowCheckedModeBanner: false,
      initialRoute: '/',
      routes: {
        '/': (_) => const HomeView()
      },
    );
  }
}

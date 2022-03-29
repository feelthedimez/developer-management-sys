import 'package:dms_admin/controllers/check_in_controller.dart';
import 'package:dms_admin/models/checks_model.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:provider/provider.dart';

class HomeView extends StatelessWidget {
  const HomeView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {

    final _controller = Get.find<CheckInController>();
    print(_controller.checkIns);

    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(_controller.checkIns.string)
            // Text("date: ${_controller.checkIns[0].date}"),
            // Text("time: ${_controller.checkIns[0].time}"),
            // Text("user late: ${_controller.checkIns[0].isLate}"),
          ],
        )
      ),
    );
  }
}



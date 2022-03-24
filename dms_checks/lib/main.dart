import 'package:flutter/material.dart';

import 'home_page.dart';

void main() {
  runApp(const MaterialApp());
}

class CheckInAndOutApp extends StatelessWidget {
  const CheckInAndOutApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: "Checks App",
      initialRoute: '/',
      routes: {
        '/': (_) => const HomePage()
      },
    );
  }
}


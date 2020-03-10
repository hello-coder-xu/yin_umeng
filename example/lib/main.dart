import 'package:flutter/material.dart';
import 'package:yin_umeng/yin_umeng.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Container(
          alignment: Alignment.center,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              RaisedButton(
                child: Text('init'),
                onPressed: () {
                  //初始化友盟
                  YinUmeng.init('5d246xxxxxxxxxxx001d5',
                      channel: 'google play', policy: Policy.BATCH, encrypt: true, reportCrash: true);
                },
              ),
              RaisedButton(
                child: Text('start'),
                onPressed: () {
                  YinUmeng.beginPageView("TestPage");
                },
              ),
              RaisedButton(
                child: Text('end'),
                onPressed: () {
                  YinUmeng.beginPageView("TestPage");
                },
              ),
              RaisedButton(
                child: Text('log'),
                onPressed: () {
                  YinUmeng.logEvent("hello");
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}

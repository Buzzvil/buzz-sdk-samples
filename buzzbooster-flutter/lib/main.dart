import 'package:flutter/material.dart';
import 'package:buzz_booster/buzz_booster.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:uuid/uuid.dart';

final buzzBooster = BuzzBooster();

void main() async {
  runApp(
    const MaterialApp(home: MyApp()),
  );
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final eventNameTextController = TextEditingController();
  final eventValuesKeyTextController = TextEditingController();
  final eventValuesValueTextController = TextEditingController();

  @override
  void initState() {
    super.initState();
    doAsyncStuff();
  }

  Future<void> doAsyncStuff() async {
    setState(() { });
    await buzzBooster.init(
      androidAppKey: '307117684877774',
      iosAppKey: '279753136766115',
    );
    await buzzBooster.startService();

    linkStream.listen((url) async {
      if (url != null) {
        showToast("move to url");
        // await Navigator.push(context, MaterialPageRoute(builder: (context) {}));
      }
    });
    return Future.value();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('BuzzBooster Example App'),
        ),
        body: Padding(
          padding: const EdgeInsets.all(20.0),
          child: Column(
            children: [
              loginWidget(),
              const SizedBox(
                height: 10,
              ),
              eventWidget(),
              OutlinedButton(
                onPressed: () async {
                  await buzzBooster.showSpecificCampaign(CampaignType.attendance);
                },
                child: const Text("Attendance Campaign"),
              ),
              OutlinedButton(
                onPressed: () async {
                  await buzzBooster.showSpecificCampaign(CampaignType.referral);
                },
                child: const Text("Referral Campaign"),
              ),
            ],
          ),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () async {
            await buzzBooster.showCampaign();
          },
          backgroundColor: Colors.deepPurple,
          child: const Icon(Icons.gif_box),
        ),
      ),
    );
  }

  Widget loginWidget() {
    return Column(
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            OutlinedButton(
              onPressed: () async {
                String userId = const Uuid().v4();
                if (userId.isNotEmpty) {
                  showToast("login");
                  await buzzBooster.setUserId(userId);
                  await buzzBooster.showInAppMessage();
                }
              },
              child: const Text("Login"),
            ),
            OutlinedButton(
              onPressed: () async {
                showToast("logout");
                await buzzBooster.setUserId(null);
              },
              child: const Text("Logout"),
            ),
          ],
        ),
      ],
    );
  }

  Widget eventWidget() {
    return Column(children: [
      const Text("Send Event"),
      TextField(
        controller: eventNameTextController,
        decoration: const InputDecoration(
          border: OutlineInputBorder(),
          labelText: 'event name',
        ),
      ),
      TextField(
        controller: eventValuesKeyTextController,
        decoration: const InputDecoration(
          border: OutlineInputBorder(),
          labelText: 'event values key',
        ),
      ),
      TextField(
        controller: eventValuesValueTextController,
        decoration: const InputDecoration(
          border: OutlineInputBorder(),
          labelText: 'event values value',
        ),
      ),
      Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          OutlinedButton(
            onPressed: () async {
              String eventName = eventNameTextController.text;
              String eventValuesKey = eventValuesKeyTextController.text;
              String eventValuesValue = eventValuesValueTextController.text;
              if (eventName.isNotEmpty) {
                await buzzBooster
                    .sendEvent(eventName, {eventValuesKey: eventValuesValue});
                showToast("send event: ${eventName}");
              } else {
                showToast("event name is required");
              }
            },
            child: const Text("Send Event"),
          ),
          OutlinedButton(
            onPressed: () async {
              eventNameTextController.clear();
              eventValuesKeyTextController.clear();
              eventValuesValueTextController.clear();
            },
            child: const Text("Clear Event"),
          ),
        ],
      ),
    ]);
  }

  void showToast(String message) {
    Fluttertoast.showToast(
        msg: message,
        toastLength: Toast.LENGTH_SHORT,
        gravity: ToastGravity.CENTER,
        timeInSecForIosWeb: 1,
        backgroundColor: Colors.black45,
        textColor: Colors.white,
        fontSize: 16.0);
  }
}

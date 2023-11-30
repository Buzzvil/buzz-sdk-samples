import 'package:flutter/material.dart';
import 'package:buzz_booster/buzz_booster.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:uuid/uuid.dart';

final buzzBooster = BuzzBooster();
const androidAppKey = '307117684877774';
const iosAppKey = '279753136766115';

void main() async {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
    doAsyncStuff();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'BuzzBooster Example App',
      initialRoute: '/',
      routes: {
        '/': (context) => HomeRoute(),
        '/optInMarketing': (context) => OptInMarketingRoute(),
      },
    );
  }

  Future<void> doAsyncStuff() async {
    setState(() {});
    await buzzBooster.init(
      androidAppKey: androidAppKey,
      iosAppKey: iosAppKey,
    );
    buzzBooster.userEventChannel =
        (String userEventName, Map<String, dynamic>? userEventValues) async {
      print("userEventDidOccur: $userEventName $userEventValues");
    };
    return Future.value();
  }
}

class HomeRoute extends StatelessWidget {
  BuzzBoosterTheme? _theme = BuzzBoosterTheme.system;
  @override
  Widget build(BuildContext context) {
    buzzBooster.optInMarketingCampaignMoveButtonClickedCallback = () async {
      Navigator.pushNamed(context, '/optInMarketing');
    };
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
              OutlinedButton(
                onPressed: () async {
                  await buzzBooster.showInAppMessage();
                },
                child: const Text("Show In-app Message"),
              ),
              OutlinedButton(
                onPressed: () async {
                  await buzzBooster.showHome();
                },
                child: const Text("Home"),
              ),
              OutlinedButton(
                onPressed: () async {
                  await buzzBooster
                      .showCampaignWithType(CampaignType.attendance);
                },
                child: const Text("Attendance Campaign"),
              ),
              OutlinedButton(
                onPressed: () async {
                  await buzzBooster.showCampaignWithType(CampaignType.referral);
                },
                child: const Text("Referral Campaign"),
              ),
              eventWidget(),
              themeWidget(),
            ],
          ),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () async {
            await buzzBooster.showHome();
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
                login();
              },
              child: const Text("Login"),
            ),
            OutlinedButton(
              onPressed: () async {
                logout();
              },
              child: const Text("Logout"),
            ),
          ],
        ),
      ],
    );
  }

  Widget eventWidget() {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: [
        OutlinedButton(
          onPressed: () async {
            await buzzBooster
                .sendEvent("bb_like", {"liked_content_id": "post_1"});
            showToast("Like: You liked post_1");
          },
          child: const Text("Like"),
        ),
        OutlinedButton(
          onPressed: () async {
            await buzzBooster.sendEvent("bb_comment",
                {"commented_content_id": "post_2", "commnet": "Greate Post!"});
            showToast("Comment: You commented post_2 with Greate Post!");
          },
          child: const Text("Comment"),
        ),
        OutlinedButton(
          onPressed: () async {
            await buzzBooster.sendEvent(
                "bb_posting_content", {"posted_content_id": "post_3"});
            showToast("Post: You posted post_3");
          },
          child: const Text("Post"),
        ),
      ],
    );
  }

  StatefulWidget themeWidget() {
    Widget buildRadioTile(BuzzBoosterTheme theme, String title,
        Function(void Function()) setState) {
      return Expanded(
        child: ListTile(
          title: Text(title),
          contentPadding: EdgeInsets.all(0),
          leading: Radio(
            value: theme,
            groupValue: _theme,
            onChanged: (BuzzBoosterTheme? value) async {
              if (value != null) {
                await buzzBooster.setTheme(value);
              }
              setState(() {
                _theme = value;
              });
            },
          ),
        ),
      );
    }

    return StatefulBuilder(builder: (context, _setState) {
      return Row(
        children: [
          buildRadioTile(BuzzBoosterTheme.light, 'Light', _setState),
          buildRadioTile(BuzzBoosterTheme.dark, 'Dark', _setState),
          buildRadioTile(BuzzBoosterTheme.system, 'System', _setState),
        ],
      );
    });
  }

  void login() async {
    String userId = const Uuid().v4();
    if (userId.isNotEmpty) {
      showToast("login");
      User user = UserBuilder(userId)
          .setOptInMarketing(false)
          .addProperty("login_type", "sns(Facebook)")
          .build();
      await buzzBooster.setUser(user);
    }
  }

  void logout() async {
    showToast("logout");
    await buzzBooster.setUser(null);
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

class OptInMarketingRoute extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('BuzzBooster Example App'),
        ),
        body: Center(
          child: Column(
            children: [
              Text("This is Sample App's Opt In Marketing Page"),
              Text("Toggle to Opt In Marketing"),
              OptInMarketingSwitch(),
              OutlinedButton(
                onPressed: () async {
                  await buzzBooster.showHome();
                },
                child: const Text("Go to see point achieved"),
              ),
              OutlinedButton(
                onPressed: () async {
                  Navigator.pop(context);
                },
                child: const Text("Back"),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class OptInMarketingSwitch extends StatefulWidget {
  const OptInMarketingSwitch({Key? key}) : super(key: key);

  @override
  _OptInMarketingSwitchState createState() => _OptInMarketingSwitchState();
}

class _OptInMarketingSwitchState extends State<OptInMarketingSwitch> {
  bool optInMarketing = false;

  @override
  Widget build(BuildContext context) {
    return Switch(
      value: optInMarketing,
      activeColor: Colors.red,
      onChanged: (bool value) {
        setState(() {
          optInMarketing = value;
          buzzBooster.sendEvent("bb_opt_in_marketing", {});
        });
      },
    );
  }
}

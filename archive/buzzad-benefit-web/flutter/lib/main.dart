import 'package:flutter/material.dart';
import 'web_view.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Benefit Web SDK',
      home: BenefitWebView(title: 'Benefit Web SDK Flutter Example'),
    );
  }
}

class BenefitWebView extends StatefulWidget {
  BenefitWebView({Key key, this.title}) : super(key: key);
  final String title;

  @override
  _BenefitWebViewState createState() => _BenefitWebViewState();
}

class _BenefitWebViewState extends State<BenefitWebView> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text(widget.title)),
        body: WebView(
          onWebViewViewCreated: _onWebViewCreated,
        )
    );
  }

  void _onWebViewCreated(WebViewController controller) {
    controller.setUrl('https://buzzvil.github.io/buzzad-benefit-sdk-publisher-web/');
  }
}

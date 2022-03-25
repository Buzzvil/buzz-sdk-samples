import UIKit
import ReactiveObjC
import BuzzBooster
import Toast

class ViewController: UIViewController {
  var scrollView: UIScrollView!
  var sendEventButton: UIButton!
  var loginButton: UIButton!
  var logoutButton: UIButton!
  var stackView: UIStackView!
  
  override func viewDidLoad() {
    super.viewDidLoad()

    setupView()
    setupLayout()
    bindEvent()
  }
  
  func setupView() {
    self.navigationItem.title = "BuzzBooster Sample";
    sendEventButton = UIButton.init(type: .system)
    loginButton = UIButton.init(type: .system)
    logoutButton = UIButton.init(type: .system)
    setButtonAttributes(button: sendEventButton, buttonTitle: "Send Event")
    setButtonAttributes(button: loginButton, buttonTitle: "Login")
    setButtonAttributes(button: logoutButton, buttonTitle: "Logout")
    
    stackView = UIStackView.init(arrangedSubviews: [
      sendEventButton,
      loginButton,
      logoutButton,
    ])
    stackView.axis = .vertical
    stackView.spacing = 8
    

    scrollView = UIScrollView.init(frame: .zero)
    scrollView.showsVerticalScrollIndicator = false;
    scrollView.addSubview(stackView)
    view.addSubview(scrollView)
  }
  
  func setButtonAttributes(button: UIButton, buttonTitle: String) {
    button.setTitle(buttonTitle, for: .normal)
    button.backgroundColor = .blue
    button.layer.cornerRadius = 8
    button.setTitleColor(.white, for: .normal)
  }
  
  func setupLayout() {
    scrollView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint .activate([
      scrollView.leadingAnchor.constraint(equalTo: view.layoutMarginsGuide.leadingAnchor),
      scrollView.trailingAnchor.constraint(equalTo: view.layoutMarginsGuide.trailingAnchor),
      scrollView.topAnchor.constraint(equalTo: view.layoutMarginsGuide.topAnchor),
      scrollView.bottomAnchor.constraint(equalTo: view.layoutMarginsGuide.bottomAnchor),
    ])
    
    stackView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint .activate([
      stackView.leadingAnchor.constraint(equalTo: scrollView.leadingAnchor),
      stackView.trailingAnchor.constraint(equalTo: scrollView.trailingAnchor),
      stackView.topAnchor.constraint(equalTo: scrollView.topAnchor),
      stackView.bottomAnchor.constraint(equalTo: scrollView.bottomAnchor),
      stackView.widthAnchor.constraint(equalTo: scrollView.widthAnchor, constant:-4)
    ])
    
    for arrangedSubview in stackView.arrangedSubviews {
      setupSizeConstraintsOfArrangedSubview(view: arrangedSubview)
    }
  }
  
  func setupSizeConstraintsOfArrangedSubview(view: UIView) {
    view.translatesAutoresizingMaskIntoConstraints = false;
    NSLayoutConstraint.activate([
      view.widthAnchor.constraint(equalTo: stackView.widthAnchor),
      view.heightAnchor.constraint(equalToConstant: 48)
    ])
  }
  
  func bindEvent() {
    registerSendEventButtonAction()
    registerLoginButtonAction()
    registerLogoutButtonAction()
  }
  
  func registerSendEventButtonAction() {
    sendEventButton.rac_command = RACCommand.init(signal: {_ in
      BuzzBooster.sendEvent(withEventName: "test event")
      self.view.window?.makeToast("test event")
      return RACSignal.empty()
    })
  }
  
  func registerLoginButtonAction() {
    loginButton.rac_command = RACCommand.init(signal: {_ in
      BuzzBooster.setUserId(AppDelegate.USER_ID)
      self.view.window?.makeToast("Login")
      return RACSignal.empty()
    })
  }
  
  func registerLogoutButtonAction() {
    logoutButton.rac_command = RACCommand.init(signal: {_ in
      BuzzBooster.setUserId(nil)
      self.view.window?.makeToast("Logout")
      return RACSignal.empty()
    })
  }
}


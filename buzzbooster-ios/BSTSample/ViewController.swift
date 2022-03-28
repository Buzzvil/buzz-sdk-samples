import UIKit
import BuzzBooster
import Toast

class ViewController: UIViewController {
  var scrollView: UIScrollView!
  var sendEventButton: UIButton!
  var loginButton: UIButton!
  var stackView: UIStackView!
  var login: Bool = false
  var count: Int = 0
  
  override func viewDidLoad() {
    super.viewDidLoad()

    setupView()
    setupLayout()
    bindEvent()
  }
  
  func setupView() {
    self.navigationItem.title = "BuzzBooster Sample"
    sendEventButton = UIButton(type: .system)
    sendEventButton.setTitle("Send Event", for: .normal)
    setButtonAttributes(button: sendEventButton)
    
    loginButton = UIButton(type: .system)
    loginButton.setTitle("Login", for: .normal)
    setButtonAttributes(button: loginButton)
    
    stackView = UIStackView(arrangedSubviews: [
      sendEventButton,
      loginButton
    ])
    stackView.axis = .vertical
    stackView.spacing = 8

    scrollView = UIScrollView(frame: .zero)
    scrollView.showsVerticalScrollIndicator = false
    scrollView.addSubview(stackView)
    view.addSubview(scrollView)
  }
  
  func setButtonAttributes(button: UIButton) {
    button.backgroundColor = .blue
    button.layer.cornerRadius = 8
    button.setTitleColor(.white, for: .normal)
  }
  
  func setupLayout() {
    scrollView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      scrollView.leadingAnchor.constraint(equalTo: view.layoutMarginsGuide.leadingAnchor),
      scrollView.trailingAnchor.constraint(equalTo: view.layoutMarginsGuide.trailingAnchor),
      scrollView.topAnchor.constraint(equalTo: view.layoutMarginsGuide.topAnchor),
      scrollView.bottomAnchor.constraint(equalTo: view.layoutMarginsGuide.bottomAnchor),
    ])
    
    stackView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
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
    view.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      view.widthAnchor.constraint(equalTo: stackView.widthAnchor),
      view.heightAnchor.constraint(equalToConstant: 48)
    ])
  }
  
  func bindEvent() {
    loginButton.addTarget(self, action: #selector(loginButtonAction), for: .touchUpInside)
    sendEventButton.addTarget(self, action: #selector(sendButtonAction), for: .touchUpInside)
  }
  
  @objc func sendButtonAction(button: UIButton!) {
    // 로그인 후, 이벤트 발생 3회 이상부터 리워드 지급
    BuzzBooster.sendEvent(withEventName: "integration")
    count+=1
    self.view.window?.makeToast("send event \(count)")
  }
  
  @objc func loginButtonAction(button: UIButton!) {
    if (self.login) {
      BuzzBooster.setUserId(nil)
      loginButton.setTitle("Login", for: .normal)
    } else {
      BuzzBooster.setUserId(AppDelegate.USER_ID)
      loginButton.setTitle("Logout", for: .normal)
    }
    self.login = !self.login
  }
}


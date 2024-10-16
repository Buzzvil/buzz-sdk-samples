import UIKit

final class LabeledTextField: UIView {
  lazy var titleLabel: UILabel = {
    let view = UILabel()
    return view
  }()
  
  lazy var textField: UITextField = {
    let view = UITextField()
    view.clearButtonMode = .always
    view.textAlignment = .right
    return view
  }()
  
  required init?(coder: NSCoder) {
    fatalError()
  }
  
  init() {
    super.init(frame: .zero)
    
    setupView()
    setupLayout()
  }
  
  private func setupView() {
    addSubview(titleLabel)
    addSubview(textField)
  }
  
  private func setupLayout() {
    titleLabel.translatesAutoresizingMaskIntoConstraints = false
    textField.translatesAutoresizingMaskIntoConstraints = false
    
    NSLayoutConstraint.activate([
      titleLabel.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 12),
      titleLabel.centerYAnchor.constraint(equalTo: centerYAnchor),
      titleLabel.widthAnchor.constraint(equalTo: widthAnchor, multiplier: 0.25),
      
      textField.leadingAnchor.constraint(equalTo: titleLabel.trailingAnchor),
      textField.trailingAnchor.constraint(equalTo: trailingAnchor),
      textField.centerYAnchor.constraint(equalTo: centerYAnchor),
    ])
  }
}

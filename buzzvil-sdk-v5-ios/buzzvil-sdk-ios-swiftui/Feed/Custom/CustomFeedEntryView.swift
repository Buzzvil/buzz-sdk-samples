import UIKit
import BuzzvilSDK

final class CustomFeedEntryView: BZVFeedEntryView {
  let customSubview1 = UIView(frame: .zero)
  let customSubview2 = UIView(frame: .zero)
  
  required init?(coder: NSCoder) {
    super.init(coder: coder)
    setupView()
  }
  
  override init(frame: CGRect) {
    super.init(frame: frame)
    setupView()
  }
  
  private func setupView() {
    // subview 초기화 및 LayoutConstraint 설정
    // ...생략...
    
    // 클릭이 가능한 영역을 지정합니다.
    clickableViews = [customSubview1, customSubview2]
  }
}

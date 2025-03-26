import BuzzvilSDK
import UIKit

class CarouselViewController: UIViewController {
  private let unitID = "YOUR_UNIT_ID"

  // 최대 10개의 광고를 요청할 수 있습니다.
  private let adRequestCount = 5
  
  // 실제 할당받은 광고의 개수입니다.
  // 이 값은 collectionView datasource의 collectionView(_:numberOfItemsInSection:) 함수의 return 값으로 사용합니다.
  private var loadedAdCount = 0
  
  // 무한루프
  private var infiniteItemCount: Int {
      return loadedAdCount * 1000
    }
  
  private let feedPromotionSlideCount = 1
  
  // 앞뒤 광고 아이템을 부분적으로 노출하기
  private let spacing: CGFloat = 8
  
  // 광고 중복 할당을 막기 위해 하나의 캐러셀에 하나의 NativeAd2Pool 인스턴스를 생성하여 사용합니다.
  private lazy var nativeGroup = BuzzNativeGroup(unitId: unitID)
  
  private lazy var flowLayout: UICollectionViewFlowLayout = {
    let flowLayout = UICollectionViewFlowLayout()
    flowLayout.scrollDirection = .horizontal
    flowLayout.minimumLineSpacing = .zero
    flowLayout.minimumInteritemSpacing = .zero
    
    // 앞뒤 광고 아이템을 부분적으로 노출하기
    // flowLayout의 minimumLineSpacing을 spacing 값으로 설정합니다.
//    flowLayout.minimumLineSpacing = spacing
    return flowLayout
  }()
  
  // 광고를 표시할 Carousel CollectionView
  private lazy var carouselCollectionView: UICollectionView = {
    let collectionView = UICollectionView(frame: .zero, collectionViewLayout: flowLayout)
    collectionView.register(CarouselCell.self, forCellWithReuseIdentifier: "CarouselCell")
    collectionView.delegate = self
    collectionView.dataSource = self
    collectionView.showsHorizontalScrollIndicator = false
    collectionView.isPagingEnabled = true
    
    // 앞뒤 광고 광고 아이템을 부분적으로 노출하기
    // isPagingEnabled를 false로 설정합니다.
//    collectionView.isPagingEnabled = false
//    // 아래 속성들을 설정합니다.
//    collectionView.decelerationRate = .fast
//    collectionView.contentInset = UIEdgeInsets(top: .zero, left: 2 * spacing, bottom: .zero, right: 2 * spacing)
    
    return collectionView
  }()
  
  private lazy var pageControl: UIPageControl = {
    let pageControl = UIPageControl(frame: .zero)
    pageControl.currentPage = .zero
    pageControl.isUserInteractionEnabled = false
    return pageControl
  }()
  
  override func viewDidLoad() {
    super.viewDidLoad()
    setupView()
    setupLayout()
    setupCarousel()
  }
  
  private func setupCarousel() {
    // BuzzNativeGroup에 광고 할당을 요청합니다.
    nativeGroup.load(
      count: adRequestCount,
      onSuccess: { [weak self] adCount in
        // 실제로 할당받은 광고의 개수(count)를 업데이트합니다.
        self?.loadedAdCount = adCount
        
        // 광고 할당이 완료되면 carouselCollectionView를 갱신합니다.
        self?.carouselCollectionView.reloadData()
        
        self?.pageControl.numberOfPages = adCount
      },
      onFailure: { [weak self] error in
        // 광고 할당 실패 시 발생하는 NSError 오류 코드에 대한 자세한 내용은 오류 코드가 나타납니다 토픽을 참고하세요.
      }
    )
  }
  
  private func setupView() {
    view.backgroundColor = .systemBackground

    view.addSubview(carouselCollectionView)
    view.addSubview(pageControl)
  }
  
  private func setupLayout() {
    // eg. auto layout constraints for carouselCollectionView
    carouselCollectionView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      carouselCollectionView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 16),
      carouselCollectionView.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor),
      carouselCollectionView.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor),
      carouselCollectionView.heightAnchor.constraint(equalTo: carouselCollectionView.widthAnchor, multiplier: 0.8),
    ])
    
    // eg. auto layout constraints for pageControl
    pageControl.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      pageControl.topAnchor.constraint(equalTo: carouselCollectionView.bottomAnchor, constant: 16),
      pageControl.centerXAnchor.constraint(equalTo: carouselCollectionView.centerXAnchor),
    ])
  }
  
  // 무한루프 구현 시
//  private func moveCarouselToMiddle() {
//    let middleIndex = ((loadedAdCount * 1000) / 2) % loadedAdCount
//    let indexPath = IndexPath(item: middleIndex, section: .zero)
//    carouselCollectionView.scrollToItem(at: indexPath, at: [.centeredVertically, .centeredHorizontally], animated: false)
//    pageControl.currentPage = indexPath.item
//  }
}

extension CarouselViewController: UICollectionViewDataSource {
  func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
    return loadedAdCount
  }
  
  func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
    guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "CarouselCell", for: indexPath) as? CarouselCell else {
      return UICollectionViewCell()
    }
    
    let native = nativeGroup.natives[indexPath.item]
    cell.bind(with: native)
    return cell
  }
}

// 무한 루프 구현 시 DataSource Extension example
//extension CarouselViewController: UICollectionViewDataSource {
//  func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
//    // 무한루프 구현 시
//    return infiniteItemCount
//  }
//  
//  // 무한루프 구현 시
//  func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
//    guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "CarouselCell", for: indexPath) as? CarouselCell else {
//      return UICollectionViewCell()
//    }
//    
//    // 할당된 고유 광고 개수로 모듈러 연산하여 index를 적용합니다.
//    let native = nativeGroup.natives[indexPath.item % loadedAdCount]
//    cell.bind(with: native)
//    
//    return cell
//  }
//}

extension CarouselViewController: UICollectionViewDelegateFlowLayout {
  func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
    return CGSize(width: collectionView.frame.size.width, height: collectionView.frame.size.height)
  }
  
  // 앞뒤 광고 아이템을 부분적으로 노출하기
//  func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
//    return CGSize(width: collectionView.frame.size.width - (4 * spacing), height: collectionView.frame.size.height)
//  }
}

extension CarouselViewController: UICollectionViewDelegate {
  func scrollViewDidScroll(_ scrollView: UIScrollView) {
    let centerOffsetX = scrollView.contentOffset.x + (scrollView.frame.width / 2)
    let pageWidth = scrollView.frame.width
    
    // pageWidth가 0이 되는 경우 division by zero를 방지합니다.
    guard pageWidth != .zero else { return }
    // 현재 캐러셀 아이템의 인덱스를 pageControl의 currentPage에 지정합니다.
    pageControl.currentPage = Int(centerOffsetX / pageWidth)
  }
  
  // 앞뒤 광고 아이템을 부분적으로 노출하기
// 캐러셀 아이템이 화면에서 일정 부분 넘어가면 다음 아이템으로 이동하도록 구현합니다.
//  func scrollViewWillEndDragging(_ scrollView: UIScrollView, withVelocity velocity: CGPoint, targetContentOffset: UnsafeMutablePointer<CGPoint>) {
//    let cellWidth = scrollView.frame.width - (4 * spacing)
//    let cellWidthWithSpace = cellWidth + spacing
//    let estimatedIndex = scrollView.contentOffset.x / cellWidthWithSpace
//    let index: Int
//    
//    if velocity.x > 0 {
//      index = Int(ceil(estimatedIndex))
//    } else if velocity.x < 0 {
//      index = Int(floor(estimatedIndex))
//    } else {
//      index = Int(round(estimatedIndex))
//    }
//    pageControl.currentPage = index
//    
//    targetContentOffset.pointee = CGPoint(x: CGFloat(index) * cellWidthWithSpace - (2 * spacing), y: .zero)
//  }
}

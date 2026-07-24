import UIKit
import BuzzvilSDK

class MissionPackViewController: UIViewController {
  let missionPack = BuzzAdBenefitMissionPack(unitId: "YOUR_MISSION_PACK_UNIT_ID")
  
  override func viewDidLoad() {
    super.viewDidLoad()
    
    showMissionPack()
  }
  
  // missionPack 사용가능한지 체크하기
  func checkCanOpenMissionPack() {
    missionPack.canOpen {
      print ("MissionPack Can open !")
    } onFailure: { error in
      print ("MissionPack Can't open error: \(error.localizedDescription)")
    }
  }
  
  // missionPack 화면에 표시하기
  func showMissionPack() {
    missionPack.show(on: self, onFailure: { error in
      print ("MissionPack Can't open error: \(error.localizedDescription)")
    })
  }
}


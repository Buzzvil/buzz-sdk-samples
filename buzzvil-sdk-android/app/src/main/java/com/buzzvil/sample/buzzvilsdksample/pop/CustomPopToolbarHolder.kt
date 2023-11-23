package com.buzzvil.sample.buzzvilsdksample.pop

import android.app.Activity
import android.graphics.Color
import android.view.View
import com.buzzvil.buzzad.benefit.pop.toolbar.DefaultPopToolbarHolder
import com.buzzvil.buzzad.benefit.pop.toolbar.PopToolbar
import com.buzzvil.sample.buzzvilsdksample.R

class CustomPopToolbarHolder: DefaultPopToolbarHolder() {
    override fun getView(activity: Activity, unitId: String): View {
        super.toolbar = PopToolbar(activity) // PopToolbar에서 제공하는 기본 Template 사용
        toolbar.setTitle("Custom Title") // 내비게이션 바 타이틀 문구를 변경합니다.
        toolbar.setIconResource(R.mipmap.ic_launcher) // 내비게이션 바 좌측 아이콘을 변경합니다.
        toolbar.setBackgroundColor(Color.LTGRAY) // 내비게이션 바 배경색을 변경합니다.

        super.addSettingsMenuItemView(activity) // 메뉴 버튼 추가 (메뉴 안에 문의하기 버튼이 있습니다)
        super.addInquiryMenuItemView(activity) // 문의하기 버튼은 이 함수를 통해 간단하게 추가 가능합니다.
        addRightMenuItemView(activity) // custom 버튼 추가
        return toolbar
    }

    // custom 버튼 추가는 toolbar.buildPopMenuItemView 를 사용하여 PopMenuImageView 를 생성하고
    // toolbar.addRightMenuButton 를 사용하여 toolbar 에 추가합니다.
    private fun addRightMenuItemView(activity: Activity) {
        val menuItemView = toolbar.buildPopMenuItemView(activity, R.drawable.ic_launcher_foreground)
        menuItemView.setOnClickListener {
            super.showInquiry(activity) // 문의하기 페이지로 연결합니다.
        }

        toolbar.addRightMenuButton(menuItemView)
    }
}

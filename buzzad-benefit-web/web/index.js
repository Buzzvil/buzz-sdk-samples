function login() {
  const userId = document.getElementById('user-id').value;
  if (!userId) {
    return;
  }
  const userProfile = {
    userId: userId,
    birthYear: 1980,
  };
  if (window.BuzzAdBenefit) {
    const genders = {
      MALE: window.BuzzAdBenefit.Gender.MALE,
      FEMALE: window.BuzzAdBenefit.Gender.FEMALE,
    }
    userProfile.gender = genders.FEMALE;
    window.BuzzAdBenefit.setUserProfile(userProfile);
    setLoginUi(userId);
  }
}

function setLoginUi(userId) {
  document.getElementById('user-id').value = userId;
  document.getElementById('user-id').disabled = true;
  document.getElementById('login-button').disabled = true;
}

function showToast(message, bad) {
  if (bad) {
    toastr.error(message);
  } else {
    toastr.info(message);
  }
}

function log(message, bad) {
  console.log(message);
  showToast(message, bad);
}

(function() {
  const userIdView = document.getElementById('user-id');
  userIdView.value += Math.random().toString(36).substring(7);
})();

/**
 * Setup BuzzAdBenefit SDK
 */
(function() {
  if (!BuzzAdBenefit) {
    return;
  }

  // Initiate SDK
  const config = {
    appId: '310600461728380'
  }

  BuzzAdBenefit.init(config);

  function loadAd() {
    setLoginUi(BuzzAdBenefit.instance.core.userProfile.userId);
    // Setup Ad Placement
    const loadConfig = {
      unitId: {
        android: '232661007718829',
        ios: '131298264757814',
      }
    }

    BuzzAdBenefit.loadAd(loadConfig)
      .then(function (nativeAd) {
        log('ON AD LOADED: An ad is loaded.');
        populateAd(nativeAd);
      }).catch(function(error) {
        log('ON LOAD ERROR: An error is detected: ' + error.message, true);
        hideAd();
      });
  }

  function onError(error) {
    console.log(error.message);
  }

  function reloadAd() {
    loadAd();
  }

  window.reloadAd = reloadAd;

  BuzzAdBenefit.ensureAuthenticated
    .then(loadAd)
    .catch(onError);

  function updateCtaView(ctaView, nativeAd) {
    var ctaTextHeader = '';
    const classReward = 'reward';
    const classParticipated = 'participated';
    ctaView.classList.remove(classReward);
    ctaView.classList.remove(classParticipated);
    if (nativeAd.reward) {
      if (nativeAd.participated) {
        ctaView.classList.add(classParticipated);
      } else {
        ctaView.classList.add(classReward);
        ctaTextHeader = '+' + nativeAd.reward + ' ';
      }
    }
    ctaView.innerText = ctaTextHeader + nativeAd.callToAction;
  }

  function renderAd(rootView, nativeAd) {
    rootView.style.display = '';
    rootView.getElementsByClassName('icon')[0].setAttribute('src', nativeAd.iconUrl);
    rootView.getElementsByClassName('name')[0].innerHTML = nativeAd.title;
    rootView.getElementsByClassName('body')[0].innerHTML = nativeAd.description;

    updateCtaView(rootView.getElementsByClassName('cta')[0], nativeAd);
  }

  function hideAd() {
    const rootView = document.getElementById('nativeAd');
    rootView.style.display = 'none';
  }

  function populateAd(nativeAd) {
    const adListener = {
      onImpressed: function(element, nativeAd) {
        log('ON IMPRESSED: The ad is impressed.');
      },
      onClicked: function(element, nativeAd) {
        log('ON CLICKED: The ad is clicked.');
      },
      onRewardRequested: function(element, nativeAd) {
        log('ON REWARD REQUESTED: Reward is requested.');
      },
      onRewarded: function(element, nativeAd, result) {
        log('ON REWARDED: The result of Reward: ' + result);
      },
      onParticipated: function(element, nativeAd) {
        log('ON PARTICIPATED: The ad is set to particiated.');
        updateCtaView(element.getElementsByClassName('cta')[0], nativeAd);
      },
      onVideoError: function(element, nativeAd, errorCode, errorMessage) {
        log('ON VIDEO ERROR: An error is detected: ' + errorCode + '\n' + errorMessage);
      },
    };

    const rootView = document.getElementById('nativeAd');
    BuzzAdBenefit.registerNativeAd(nativeAd, rootView, adListener);
    renderAd(rootView, nativeAd);
  }
})();

function login() {
  const userProfile = {
    userId: 'TEST_USER_ID',
    birthYear: 1980,
  };
  if (window.BuzzAdBenefit) {
    const genders = {
      MALE: window.BuzzAdBenefit.Gender.MALE,
      FEMALE: window.BuzzAdBenefit.Gender.FEMALE,
    }
    userProfile.gender = genders.FEMALE;
    window.BuzzAdBenefit.setUserProfile(userProfile);
  }
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

  var ads = [];

  function loadAd() {
    // Setup Ad Placement
    const loadConfig = {
      unitId: {
        android: '232661007718829',
        ios: '131298264757814',
      },
      count: 3
    }

    BuzzAdBenefit.loadAd(loadConfig)
      .then(function (nativeAds) {
        log('ON AD LOADED: An ad is loaded.');
        ads = ads.concat(nativeAds);
        populateAd(ads.shift());
      }).catch(function(error) {
        log('ON LOAD ERROR: An error is detected: ' + error.message, true);
      });
  }

  function onError(error) {
    console.log(error.message);
  }

  function reloadAd() {
    if (ads.length) {
      populateAd(ads.shift());
    } else {
      loadAd();
    }
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

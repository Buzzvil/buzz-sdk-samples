import { Button } from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import React from 'react';
import styles from '../styles';
import { BuzzBooster } from 'react-native-buzz-booster';
import { Toast } from 'react-native-toast-message/lib/src/Toast';

export default () => {
  return (
    <SafeAreaView style={styles.columnContainer}>
      <Button title='Like' onPress={like} />
      <Button title='Comment' onPress={comment} />
      <Button title='Post' onPress={post} />
    </SafeAreaView >
  );
}

function like() {
  BuzzBooster.sendEvent('bb_like', { 'liked_content_id': 'post_1' });
  Toast.show({
    type: 'success',
    text1: 'Like',
    text2: 'You liked post_1',
    position: 'bottom',
  });
}

function comment() {
  BuzzBooster.sendEvent('bb_comment', { 'commented_content_id': 'post_2', 'comment': 'Great Post!' });
  Toast.show({
    type: 'success',
    text1: 'Comment',
    text2: 'You commented post_2 with "Great Post!"',
    position: 'bottom',
  });
}

function post() {
  BuzzBooster.sendEvent('bb_posting_content', { 'posted_content_id': 'post_3' });
  Toast.show({
    type: 'success',
    text1: 'Post',
    text2: 'You posted post_3',
    position: 'bottom',
  });
}

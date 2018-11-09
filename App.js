/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
      console.log("S: ", KFullscreenVideo)
 */
/*<Video source={{uri: "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"}}
            ref={(ref) => {
                this.player = ref
            }}
            onBuffer={this.onBuffer}
            onEnd={this.videoError}
            style={styles.backgroundVideo} />*/

import React, {Component} from 'react'
import {Platform, StyleSheet, Text, View, Button} from 'react-native'
//import Video from 'react-native-video'
import {NativeModules} from 'react-native'

//const KFullscreenVideo = NativeModules.KFullscreenVideo.playFullscreenVideo

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android:
    'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

type Props = {};
export default class App extends Component<Props> {
  render() {
    return (
      <View style={styles.container}>
        
        <Button
            title = "stuff"
            onPress={() => NativeModules.KFullscreenVideoPlayer.playFullscreenVideoA()}
        />
    </View>
    );
  }
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
    backgroundVideo: {
        position: 'absolute',
        top: 0,
        left: 0,
        bottom: 0,
        right: 0,
    },
});

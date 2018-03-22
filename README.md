# shadow-cljs + firebase

Example showing authentication with Google Auth and persistence with realtime database using JS SDK wrapped in Clojure functions

## Demo
[Demo](https://shadow-firebase.firebaseapp.com)

## How to use?

``` shell
npm install -g shadow-cljs

# or with yarn

yarn global add shadow-cljs
```

Set up firebase:

- Create a project at the [Firebase console](https://console.firebase.google.com/).
- Get your credentials from the Firebase console and replace `initializeApp #js` at app.core/firebase-init
- Firebase console again, go to authentication>signup method and enable Google.

`git clone` this repo and `cd` into the repo, then:

``` shell
yarn install

yarn dev
```

## Clean

``` shell
yarn clean
```

## Release

``` shell
yarn release
```

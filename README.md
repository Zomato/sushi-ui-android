# Sushi Android UI Kit

## Usage
The master branch is being used for release and dev is the default branch.

### Installation
This package is available via Github Package Registry. To use this, follow these steps.

Add the Github Maven repository and the dependency in your app's build.gradle

```groovy
repositories {
    // ... google(), jcenter() etc
    maven {
        url "https://maven.pkg.github.com/Zomato/sushi-ui-android"
        credentials(HttpHeaderCredentials) {
            name = "Authorization"
            value = "token ${System.getenv("GITHUB_TOKEN")}"
        }
        authentication { header(HttpHeaderAuthentication) }
    }
}

dependencies {
    // ... other dependencies
    implementation "com.zomato.sushilib:sushilib-android:${latest_version}"
}

```

> NOTE: Make sure you have the `GITHUB_TOKEN` environment variable set. This token should have `read:packages` enabled.


## Documentation
A delicious UI Kit to build Android apps. Made with ❤ by Zomato <br />
<https://zomato.github.io/sushi-ui-android/>

## Testing & Coverage

Run all tests and get coverage report
```shell 
./gradlew jacocoTestReport
```

## Publishing
To publish this package, go to Actions tab of the repo and select "Version Bump CI" workflow. This workflow has to be manually triggered by clicking "Run Workflow" button. It will create a PR on "master" branch which after merge will generate and publish a new package.


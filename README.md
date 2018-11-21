Aspect
====
ImageView & FrameLayout implementations that respect applied aspect ratio!

## Download

Download the latest version from [releases][1].

Or get it using **Gradle:**

````groovy
dependencies {
    compile 'com.subinkrishna:aspect:1.3'
}
````

Or **Maven:**

````xml
<dependency>
  <groupId>com.subinkrishna</groupId>
  <artifactId>aspect</artifactId>
  <version>1.3</version>
</dependency>
````

## Usage

**XML:**

````xml
<!-- AspectRatioImageView -->
<com.subinkrishna.aspect.AspectRatioImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:lock="width"
        app:ratio="1"/>

<!-- AspectRatioFrameLayout -->
<com.subinkrishna.aspect.AspectRatioFrameLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:lock="width"
        app:ratio="1.5"/>
````

**Java:**

````java
AspectRatioImageView imageView = getView();
imageView
    // Sets the aspect ratio (ratio = width / height)
    // Use 0 (zero) to ignore the ratio.
    .ratio(ratio)
    // Locks a dimension and adjusts the other dimension based on the
    // specified aspect ratio
    .lock(AspectRatioLayout.WIDTH);

// Load image
Picasso.with(context)
    .load(input.url)
    .resize(targetWidth, targetHeight)
    .into(imageView);
````

## License

    Copyright (C) 2018 Subinkrishna Gopi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: ../../releases

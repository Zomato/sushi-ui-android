<template>
  <div class="warning custom-block">
    <p class="custom-block-title">NOTE</p>
    <p>
      For all the four drawable properties, use <code>app:drawableLeft</code>
      and <strong>never</strong> <code>android:drawableLeft</code>.

      Behaviour with <code>drawableTop</code> and <code>drawableBottom</code>
      is also unspecified. This view only handles horizontal drawables properly.
    </p>
  </div>
</template>

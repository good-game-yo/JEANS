const slideList = document.querySelector('.look_flick_camera');  // Slide parent dom
<<<<<<< HEAD
    const slideContents = document.querySelectorAll('.flick_panel');  // each slide dom
    const slideBtnNext = document.querySelector('#look_slide_button_right'); // next button
    const slideBtnPrev = document.querySelector('#look_slide_button_left'); // prev button
    const slideLen = slideContents.length;  // slide length
    const slideWidth = 300; // slide width
    const slideSpeed = 300; // slide speed
    const startNum = 0; // initial slide index (0 ~ 4)
 
 function slide() {
    
    slideList.style.width = slideWidth * (slideLen + 2) + "px";
    
=======
const slideContents = document.querySelectorAll('.flick_panel');  // each slide dom
const slideBtnNext = document.querySelector('#look_slide_button_right'); // next button
const slideBtnPrev = document.querySelector('#look_slide_button_left'); // prev button
const slideLen = slideContents.length;  // slide length
const slideWidth = 300; // slide width
const slideSpeed = 300; // slide speed
const startNum = 0; // initial slide index (0 ~ 4)

function slide() {

    slideList.style.width = slideWidth * (slideLen + 2) + "px";

>>>>>>> 51be9a60cb1ebb10d37042d19286ad5567359aca
    // Copy first and last slide
    let firstChild = slideList.firstElementChild;
    let lastChild = slideList.lastElementChild;
    let clonedFirst = firstChild.cloneNode(true);
    let clonedLast = lastChild.cloneNode(true);

    // Add copied Slides
    slideList.appendChild(clonedFirst);
    slideList.insertBefore(clonedLast, slideList.firstElementChild);

    slideList.style.transform = "translate3d(-" + (slideWidth * (startNum + 1)) + "px, 0px, 0px)";

    let curIndex = startNum; // current slide index (except copied slide)
    let curSlide = slideContents[curIndex]; // current slide dom
    curSlide.classList.add('slide_active');


    /** Next Button Event */
    slideBtnNext.addEventListener('click', function() {
<<<<<<< HEAD
      if (curIndex <= slideLen - 1) {
        slideList.style.transition = slideSpeed + "ms";
        slideList.style.transform = "translate3d(-" + (slideWidth * (curIndex + 2)) + "px, 0px, 0px)";
      }
      if (curIndex === slideLen - 1) {
        setTimeout(function() {
          slideList.style.transition = "0ms";
          slideList.style.transform = "translate3d(-" + slideWidth + "px, 0px, 0px)";
        }, slideSpeed);
        curIndex = -1;
      }
      curSlide.classList.remove('slide_active');
      curSlide = slideContents[++curIndex];
      curSlide.classList.add('slide_active');
=======
        if (curIndex <= slideLen - 1) {
            slideList.style.transition = slideSpeed + "ms";
            slideList.style.transform = "translate3d(-" + (slideWidth * (curIndex + 2)) + "px, 0px, 0px)";
        }
        if (curIndex === slideLen - 1) {
            setTimeout(function() {
                slideList.style.transition = "0ms";
                slideList.style.transform = "translate3d(-" + slideWidth + "px, 0px, 0px)";
            }, slideSpeed);
            curIndex = -1;
        }
        curSlide.classList.remove('slide_active');
        curSlide = slideContents[++curIndex];
        curSlide.classList.add('slide_active');
>>>>>>> 51be9a60cb1ebb10d37042d19286ad5567359aca
    });

    /** Prev Button Event */
    slideBtnPrev.addEventListener('click', function() {
<<<<<<< HEAD
      if (curIndex >= 0) {
        slideList.style.transition = slideSpeed + "ms";
        slideList.style.transform = "translate3d(-" + (slideWidth * curIndex) + "px, 0px, 0px)";
      }
      if (curIndex === 0) {
        setTimeout(function() {
          slideList.style.transition = "0ms";
          slideList.style.transform = "translate3d(-" + (slideWidth * slideLen) + "px, 0px, 0px)";
        }, slideSpeed);
        curIndex = slideLen;
      }
      curSlide.classList.remove('slide_active');
      curSlide = slideContents[--curIndex];
      curSlide.classList.add('slide_active');
    });

   
  }

  function init(){
      slide();
  }

  init();
=======
        if (curIndex >= 0) {
            slideList.style.transition = slideSpeed + "ms";
            slideList.style.transform = "translate3d(-" + (slideWidth * curIndex) + "px, 0px, 0px)";
        }
        if (curIndex === 0) {
            setTimeout(function() {
                slideList.style.transition = "0ms";
                slideList.style.transform = "translate3d(-" + (slideWidth * slideLen) + "px, 0px, 0px)";
            }, slideSpeed);
            curIndex = slideLen;
        }
        curSlide.classList.remove('slide_active');
        curSlide = slideContents[--curIndex];
        curSlide.classList.add('slide_active');
    });


}

function init(){
    slide();
}

init();
>>>>>>> 51be9a60cb1ebb10d37042d19286ad5567359aca

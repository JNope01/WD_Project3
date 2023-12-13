 function showInstructorDialog(Jeff) {
            alert("Good Choice!")
            // You can replace the alert with your own custom dialog implementation
        }
        $(document).ready(function () {
            // This code runs when the document (HTML page) is fully loaded and ready
        
            // Select the element with the class "scroll-down-message" when it is clicked
            $(".scroll-down-message").click(function () {
                // When the element is clicked, animate the scroll to the top of the element with the class "container"
                $("html, body").animate(
                    {
                        scrollTop: $(".container").offset().top,
                    },
                    800
                );
            });
        });
       
let slideIndex = 0;
showSlides();

function showSlides() {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  let dots = document.getElementsByClassName("dot");
  if (slides.length > 0 && slideIndex >= 0 && slideIndex < slides.length) {
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex].style.display = "block";  
  dots[slideIndex].className += " active";
  // Increment slideIndex or reset to 0
  slideIndex = (slideIndex + 1) % slides.length;
  
}
setTimeout(showSlides, 4000); // Change image every 2 seconds
}

 


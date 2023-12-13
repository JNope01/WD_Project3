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
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }
  slideIndex++;
  if (slideIndex > slides.length) {slideIndex = 1}    
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
  setTimeout(showSlides, 2000); // Change image every 2 seconds
}

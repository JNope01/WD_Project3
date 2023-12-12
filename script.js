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
        // Add this to your JavaScript file
const slider = document.getElementById("slider");
const slides = [
    "path/to/image1.jpg",
    "path/to/image2.jpg",
    "path/to/image3.jpg",
    // Add more image paths as needed
];
let currentSlide = 0;

function showSlides() {
    slider.style.transform = `translateX(${-currentSlide * 100}%)`;
}

function nextSlide() {
    if (currentSlide < slides.length - 1) {
        currentSlide++;
    } else {
        currentSlide = 0;
    }
    showSlides();
}

function prevSlide() {
    if (currentSlide > 0) {
        currentSlide--;
    } else {
        currentSlide = slides.length - 1;
    }
    showSlides();
}

// Display the initial set of slides
showSlides();

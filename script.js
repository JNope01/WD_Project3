 function showInstructorDialog(Jeff) {
            alert("You clicked on " + Jeff + "'s button!")
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
        
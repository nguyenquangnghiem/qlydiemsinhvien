import { Carousel } from "react-bootstrap";

const HomeAdmin = () => {
  const images = [
    "https://res.cloudinary.com/dhcvsbuew/image/upload/v1693030473/h6_vnmhrs.jpg",
    "https://res.cloudinary.com/dhcvsbuew/image/upload/v1693029931/h2_rxfaqf.png",
    "https://res.cloudinary.com/dhcvsbuew/image/upload/v1693029930/h3_slbx97.jpg",
  ];

  return (
    <>
      <Carousel>
        {images.map((src, index) => (
          <Carousel.Item key={index}>
            <img
              className="d-block w-100"
              src={src}
              alt={`Slide ${index + 1}`}
            />
          </Carousel.Item>
        ))}
      </Carousel>
    </>
  );
};

export default HomeAdmin;

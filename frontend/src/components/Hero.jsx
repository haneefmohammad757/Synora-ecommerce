import "./Hero.css";

import { useEffect, useMemo, useState } from "react";
import { useNavigate } from "react-router-dom";

import {
    FaChevronLeft,
    FaChevronRight,
    FaStar,
    FaBolt,
    FaShippingFast
} from "react-icons/fa";

const Hero = ({ products }) => {

    const navigate = useNavigate();

    const [current, setCurrent] = useState(0);

    // Use products received from Home.jsx
    const heroProducts = useMemo(() => {

        return products
            .filter(product => product.heroProduct)
            .sort((a, b) =>
                (a.heroOrder || 999) -
                (b.heroOrder || 999)
            );

    }, [products]);

    useEffect(() => {

        if (!heroProducts.length) return;

        const interval = setInterval(() => {

            setCurrent(prev =>

                prev === heroProducts.length - 1

                    ? 0

                    : prev + 1

            );

        }, 4500);

        return () => clearInterval(interval);

    }, [heroProducts]);

    const nextSlide = () => {

        setCurrent(prev =>

            prev === heroProducts.length - 1

                ? 0

                : prev + 1

        );

    };

    const previousSlide = () => {

        setCurrent(prev =>

            prev === 0

                ? heroProducts.length - 1

                : prev - 1

        );

    };

    if (!heroProducts.length) {

        return null;

    }

    const product = heroProducts[current];

    const finalPrice =

        product.discount > 0

            ?

            Math.round(

                product.price *

                (1 - product.discount / 100)

            )

            :

            product.price;

  return (

    <section className="hero">

        <button

            className="hero-arrow hero-left"

            onClick={previousSlide}

        >

            <FaChevronLeft />

        </button>

        <div className="hero-wrapper">

            <div className="hero-content">

                <span className="hero-tag">

                    <FaBolt />

                    Trending Collection

                </span>

                <h1>

                    {product.name}

                </h1>

                <h3>

                    {product.brand}

                </h3>

                <p>

                    {product.description}

                </p>

                <div className="hero-rating">

                    <FaStar />
                    <FaStar />
                    <FaStar />
                    <FaStar />
                    <FaStar />

                    <span>

                        4.9

                    </span>

                </div>

                <div className="hero-price">

                    <h2>

                        ₹ {finalPrice}

                    </h2>

                    {

                        product.discount > 0 &&

                        <>

                            <span className="old-price">

                                ₹ {product.price}

                            </span>

                            <span className="discount">

                                {product.discount}% OFF

                            </span>

                        </>

                    }

                </div>

                <div className="hero-features">

                    <span>

                        <FaShippingFast />

                        Free Delivery

                    </span>

                    <span>

                        7 Days Return

                    </span>

                    <span>

                        Secure Payment

                    </span>

                </div>

                <div className="hero-buttons">

                    <button

                        className="shop-now-btn"

                        onClick={() =>
                            navigate(`/product/${product.id}`)
                        }

                    >

                        Shop Now

                    </button>

                    <button

                        className="explore-btn"

                        onClick={() =>

                            document

                                .getElementById("products")

                                ?.scrollIntoView({

                                    behavior:"smooth"

                                })

                        }

                    >

                        Explore More

                    </button>

                </div>

            </div>

            <div

                className="hero-image"

                onClick={() =>
                    navigate(`/product/${product.id}`)
                }

            >

                <div className="hero-sale">

                    {product.discount}% OFF

                </div>

                <img

                    src={product.imageUrl}

                    alt={product.name}

                />

            </div>

        </div>

        <button

            className="hero-arrow hero-right"

            onClick={nextSlide}

        >

            <FaChevronRight />

        </button>

        <div className="hero-thumbnails">

            {

                heroProducts.map((item,index)=>(

                    <img

                        key={item.id}

                        src={item.imageUrl}

                        alt={item.name}

                        className={

                            current===index

                            ?

                            "thumb active"

                            :

                            "thumb"

                        }

                        onClick={()=>

                            setCurrent(index)

                        }

                    />

                ))

            }

        </div>

        <div className="hero-indicators">

            {

                heroProducts.map((_,index)=>(

                    <span

                        key={index}

                        className={

                            current===index

                            ?

                            "indicator active"

                            :

                            "indicator"

                        }

                        onClick={()=>

                            setCurrent(index)

                        }

                    />

                ))

            }

        </div>

    </section>

);

};

export default Hero;
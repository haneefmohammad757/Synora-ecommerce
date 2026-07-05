import "./Deals.css";

import { useEffect, useMemo, useState } from "react";

import ProductCard from "./ProductCard";

const Deals = ({ products }) => {

    const [timeLeft, setTimeLeft] = useState({

        hours:12,

        minutes:45,

        seconds:30

    });

    const dealProducts = useMemo(() => {

        return products.slice(0,4);

    }, [products]);

    useEffect(() => {

        const timer = setInterval(() => {

            setTimeLeft(prev => {

                let { hours, minutes, seconds } = prev;

                if(seconds > 0){

                    seconds--;

                }

                else{

                    seconds = 59;

                    if(minutes > 0){

                        minutes--;

                    }

                    else{

                        minutes = 59;

                        if(hours > 0){

                            hours--;

                        }

                        else{

                            hours = 12;

                            minutes = 45;

                            seconds = 30;

                        }

                    }

                }

                return {

                    hours,

                    minutes,

                    seconds

                };

            });

        },1000);

        return () => clearInterval(timer);

    },[]);

    return(

        <section className="deals">

            <div className="deal-header">

                <div>

                    <h2>

                        ⚡ Today's Deals

                    </h2>

                    <p>

                        Limited time offers

                    </p>

                </div>

                <div className="timer">

                    <div>

                        {timeLeft.hours}h

                    </div>

                    <div>

                        {timeLeft.minutes}m

                    </div>

                    <div>

                        {timeLeft.seconds}s

                    </div>

                </div>

            </div>

            <div className="deal-grid">

                {

                    dealProducts.map(product => (

                        <ProductCard

                            key={product.id}

                            product={product}

                        />

                    ))

                }

            </div>

        </section>

    );

};

export default Deals;
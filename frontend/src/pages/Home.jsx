import { useState, useEffect } from "react";

import axios from "../api/axios";

import Navbar from "../components/Navbar";
import Hero from "../components/Hero";
import Deals from "../components/Deals";
import CategorySection from "../components/CategorySection";
import ProductGrid from "../components/ProductGrid";
import WhyChooseUs from "../components/WhyChooseUs";
import Newsletter from "../components/Newsletter";
import Footer from "../components/Footer";
import Loader from "../components/Loader";

function Home() {

    const [search, setSearch] = useState("");

    const [selected, setSelected] = useState("All");

    const [products, setProducts] = useState([]);

    const [loading, setLoading] = useState(true);

    const currentUser = JSON.parse(localStorage.getItem("user"));

    useEffect(() => {

        loadProducts();

    }, []);

    const loadProducts = async () => {

        try {

            const res = await axios.get("/products");

            setProducts(res.data);

        }

        catch (err) {

            console.log(err);

        }

        finally {

            setLoading(false);

        }

    };

    if (loading) {

        return <Loader />;

    }

    return (

        <>

            <Navbar

                search={search}

                setSearch={setSearch}

            />

            <Hero

                products={products}

            />

            <CategorySection

                selected={selected}

                setSelected={setSelected}

            />

            <ProductGrid

                products={products}

                search={search}

                selected={selected}

            />

            <Deals

                products={products}

            />

            {

                !currentUser &&

                (

                    <>

                        <WhyChooseUs />

                        <Newsletter />

                    </>

                )

            }

            <Footer />

        </>

    );

}

export default Home;
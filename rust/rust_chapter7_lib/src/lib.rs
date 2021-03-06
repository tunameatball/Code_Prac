#[cfg(test)]
mod tests {
    #[test]
    fn it_works() {
        assert_eq!(2 + 2, 4);
    }
}

mod front_of_house{
    
    pub mod hosting{
    
        pub fn add_to_waitlist(){

        }
        
        fn seat_at_table(){

        }
    }

    mod serving{
        fn take_order(){

        }
        
        fn serve_order(){

        }

        fn take_payment(){

        }
    }
}

use self::front_of_house::hosting;


pub fn eat_at_restaurant(){
    // 절대 경로
    crate::front_of_house::hosting::add_to_waitlist();
    
    // 상대 경로
    front_of_house::hosting::add_to_waitlist();

    let order1 = back_of_house::Appetizer::Soup;
    let order2 = back_of_house::Appetizer::Salad;
}


fn serve_order(){

}

mod back_of_house{
    fn fix_incorrect_order(){
            cook_order();
            super::serve_order();
        
    }

    fn cook_order(){

    }

    pub enum Appetizer{
        Soup,
        Salad,
    }
}

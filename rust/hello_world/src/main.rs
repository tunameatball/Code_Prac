fn main() {
    println!("Hello, world!");

    println!("{}, {}, {}, {}", 19 % 10, 12 / 10, 9 / 10, 9 % 10);
    
    let test = String::from("12");
    
    let x = 123;
    for i in x.as_ne_bytes(){
        println!("{}", i);
    }

    println!("{}", test.len()>2);

    // let i = 0b001;
    // let i = 0b111;
    // let s = "  #";
}

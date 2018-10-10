/**
 * Pollard Rho Factorization Method
 *
 * @author Isaac Flaum
 */
const numberToFactor = 36287; // large integer we want to factor with pollard rho method

const seed = 2; // Integer seed value or guess

const numberOfIterations = 20; // number of iterations to run polynomial. Note this should be an even number to make
  //the guessing of (x_2n - x_n) reach the last n.

const polynomial = x => (x * x) + 1; // The polynomial f(x) = x^2 + 1

let polynomialIterations = []; // a list to store all of the recursive iterations of the polynomial

/**
 * Euclidian algorithm to find GCD of two integers. I changed the function a bit but og credit to Stack Overflow
 * https://stackoverflow.com/questions/17445231/js-how-to-find-the-greatest-common-divisor
 */
const gcd = (a, b) => {
  if ((typeof a !== 'number') || (typeof b !== 'number')) {
    throw new Error('One of the arguments was not a number!');
  }
  a = Math.abs(a);
  b = Math.abs(b);
  while(b) {
    var t = b;
    b = a % b;
    a = t;
  }
  return a;
};

// generate our guesses
let result = seed;
for (var i = 0; i < 20; i++) {
	result = polynomial(result) % numberToFactor;
	console.log(`Adding f(${i + 1}) = ${result} to the results list.`)
	polynomialIterations.push(result);
}

// test our guesses
for (var i = 0; i < numberOfIterations / 2; i++) {
	console.log(`Testing gcd of ${numberToFactor} and ${polynomialIterations[2*(i+ 1) - 1]} - ${polynomialIterations[i]}`)
    let d = gcd(polynomialIterations[2*(i+ 1) - 1] - polynomialIterations[i], numberToFactor);
    if (d > 1) {
    	console.log(`Found a factor: x = ${d}`);
    	process.exit(0);
    }
}
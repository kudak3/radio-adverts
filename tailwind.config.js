/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main/resources/templates/**/*.{html,js}"],
  theme: {
    extend: {
      backgroundImage: {
        'radio-ads': "url('/img/radio-ads.jpg')",
        'login': "url('/img/login.jpg')",
      }
    },
  },
  plugins: [],
}


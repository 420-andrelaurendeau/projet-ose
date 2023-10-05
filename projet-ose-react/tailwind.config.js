const plugin = require("tailwindcss/plugin");
module.exports = {
    content: ['./src/**/*.{js,jsx,ts,tsx}', './public/index.html'],
    darkMode: 'media', // Utilise le mode sombre en fonction des préférences du système
    theme: {
        extend: {
            colors: {
                'blue': '#306bac',
                'orange': '#F57A00',
                'gray': '#D0CCD0',
                'offwhite': '#FBFCFF',
                'dark': '#121420',
                'softdark': '#1A1C23',
            },
        },
    },
    variants: {
        extend: {
            borderColor: ['focus-visible'],
            opacity: ['disabled'],
        },
    },
    plugins: [
        plugin(function({addUtilities}) {
            addUtilities({
                '.no-scrollbar': {
                    '-ms-overflow-style': 'none',
                    'scrollbar-width': 'none'
                }
            })
        })
    ],
}
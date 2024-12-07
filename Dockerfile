FROM php:8.1.1-apache

# Install mysqli extension
RUN docker-php-ext-install mysqli
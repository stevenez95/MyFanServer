// grab our gulp packages
var gulp  = require('gulp'),
    gutil = require('gulp-util'),
    uglify = require('gulp-uglify'),
    concat = require('gulp-concat');

gulp.task('build-js', function() {
  return gulp.src('js/**/*.js')
    .pipe(concat('bundle.js'))
      //only uglify if gulp is ran with '--type production'
    .pipe(uglify({mangle:false})) 
    .pipe(gulp.dest('js/app.min.js'));
});

gulp.task('watch',function (){
   gulp.watch('public_html/js/**/*.js',['build-js']); 
});

gulp.task('default', ['build-js']);
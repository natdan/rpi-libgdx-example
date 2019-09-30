#ifdef GL_ES
	precision highp float;
#endif

uniform sampler2D u_image;
uniform vec2 u_mouse_position;

varying vec2 v_texCoords;

const float factor = 0.001;
const float step = 8.0;

void main ()
{
	vec4 pointColour = texture2D(u_image, v_texCoords);

      // float xStepDivisor = step / (1024.0);
      // float yStepDivisor = step / (768.0);
      float xStepDivisor = step / (640.0);
      float yStepDivisor = step / (480.0);

      float dx = u_mouse_position.x - v_texCoords.x;
      float dy = u_mouse_position.y - v_texCoords.y;
      float distance = sqrt(dx * dx + dy * dy); 

      if (distance > 1.0) { distance = 1.0; }
      distance = 1.0 - distance;

      vec4 distanceColour = vec4(distance, distance, distance, 1.0);

      // gl_FragColor = vec4(distance, distance, distance, 1);
      
      dx = (u_mouse_position.x - v_texCoords.x);
      dy = (u_mouse_position.y - v_texCoords.y);
      
      float adx = abs(dx);
      float ady = abs(dy);
      
      float x0 = u_mouse_position.x;
      float y0 = u_mouse_position.y;
      float x1 = v_texCoords.x;
      float y1 = v_texCoords.y;


      float k = 0.0;
      if (adx > ady) {
          k = ady / adx;
      } else {
          k = adx / ady;
      }

      vec4 pixel = texture2D(u_image, vec2(x0, y0));

      for (float i = 0.0; i < 640.0; i += step) {
         pixel = texture2D(u_image, vec2(x0, y0));

         if (pixel.a > 0.0) {
            distanceColour.r = distanceColour.r - factor * step;
            distanceColour.g = distanceColour.g - factor * step;
            distanceColour.b = distanceColour.b - factor * step;

            distanceColour.r = distanceColour.r + pixel.r * factor * step;
            distanceColour.g = distanceColour.g + pixel.g * factor * step;
            distanceColour.b = distanceColour.b + pixel.b * factor * step;
         }

         if (adx > ady) {
            if (i / 640.0 > adx) { break; }
            if (y0 < y1) {
               y0 += k * yStepDivisor;
            } else {
               y0 -= k * yStepDivisor;
            }
            if (x0 < x1) {
               x0 += xStepDivisor;
            } else {
               x0 -= xStepDivisor;
            }
         } else {
            if (i / 640.0 > ady) { break; }
            if (x0 < x1) {
               x0 += k * xStepDivisor;
            } else {
               x0 -= k * xStepDivisor;
            }
            if (y0 < y1) {
               y0 += yStepDivisor;
            } else {
               y0 -= yStepDivisor;
            }
         }
      }      

      if (pointColour.a != 0.0) {    
         gl_FragColor = pointColour - (vec4(1, 1, 1, 0) - distance);
      } else {
         gl_FragColor = distanceColour; // vec4(distance, distance, distance, 1);
      }
}

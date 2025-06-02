FROM public.ecr.aws/lambda/provided:al2023

COPY bootstrap ${LAMBDA_RUNTIME_DIR}
COPY target/demo ${LAMBDA_TASK_ROOT}/func

EXPOSE 8080

CMD ["com.example.FunctionLambdaRuntime"]
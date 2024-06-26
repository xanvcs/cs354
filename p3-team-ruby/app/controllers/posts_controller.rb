class PostsController < ApplicationController
  before_action :set_post, only: %i[ show edit update destroy ]

  # GET /posts or /posts.json
  def index
    redirect_to new_user_session_path unless user_signed_in?
    @posts = Post.all.order(created_at: :desc)
    @post = Post.new
  end

  # GET /posts/1 or /posts/1.json
  def show
  end

  # GET /posts/new
  def new
    @post = Post.new
  end

  # GET /posts/1/edit
  def edit
  end

  # POST /posts or /posts.json
  def create
    @post = current_user.posts.build(post_params)
    respond_to do |format|

      if @post.save
        format.html { redirect_to posts_path }
        format.json { render :show, status: :created, location: @post }
      else
          format.turbo_stream { render turbo_stream: turbo_stream.replace(@post, partial: 'posts/form', locals: { post: @post }) }
          format.html { render :new, status: :unprocessable_entity }
          format.turbo_stream { render turbo_stream: turbo_stream.replace(@post, partial: 'posts/form', locals: { post: @post }) }
          format.html { render :new, status: :unprocessable_entity }
        format.json { render json: @post.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /posts/1 or /posts/1.json
  def update
    respond_to do |format|
      if @post.update(post_params)
        format.html { redirect_to post_url(@post), notice: "Post was successfully updated." }
        format.json { render :show, status: :ok, location: @post }
      else
        format.html { render :edit, status: :unprocessable_entity }
        format.json { render json: @post.errors, status: :unprocessable_entity }
      end
    end
  end

  def upvote
    @post = Post.find(params[:id])
    vote = @post.votes.find_or_initialize_by(user: current_user)

    if vote.value == -1
      @post.likes += 2
    elsif vote.new_record?
      @post.likes += 1
    end

    vote.update(value: 1)
    @post.save

    redirect_to posts_path
  end

  def downvote
    @post = Post.find(params[:id])
    vote = @post.votes.find_or_initialize_by(user: current_user)

    if vote.value == 1
      @post.likes -= 2
    else
      @post.likes -= 1
    end

    vote.update(value: -1)
    @post.save

    redirect_to posts_path
  end

  # DELETE /posts/1 or /posts/1.json
  def destroy
    @post.destroy!

    respond_to do |format|
      format.html { redirect_to posts_url, notice: "Post was successfully destroyed." }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_post
      @post = Post.find(params[:id])
    end

    # Only allow a list of trusted parameters through.
    def post_params
      params.require(:post).permit(:body, :comments, :likes, :image)
    end
end
